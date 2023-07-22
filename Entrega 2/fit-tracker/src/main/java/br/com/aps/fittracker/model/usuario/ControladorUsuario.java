package br.com.aps.fittracker.model.usuario;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class ControladorUsuario { //extends UserDetailsServiceAutoConfiguration {
    
    @Autowired
    private CadastroUsuario usuarioCadastro;

    /*
    @Value("${app.jwtSecret}") // Defina a chave secreta no arquivo application.properties
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}") // Defina o tempo de expiração do token no arquivo application.properties
    private long jwtExpirationMs; */

    public void inserir(Usuario usuario) {
        System.out.println("Inserindo usuário");
        System.out.println(usuario);
        // Verificações de validação
        if (!isEmailValido(usuario.getEmail())) {
            throw new IllegalArgumentException("Email inválido.");
        } else if (buscarPorEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        } else if (usuario.getSenha().length() < 6) {
            throw new IllegalArgumentException("A Senha deve ter no mínimo 6 caracteres.");
        } 
        // Criptografa a senha
        usuario.setSenha(hashPassword(usuario.getSenha()));
        // Cadastra o usuário
        usuarioCadastro.inserir(usuario);
    }

    public boolean login(String email, String senha) {
        boolean resposta = true;
        Usuario usuario = buscarPorEmail(email);
        if (usuario == null || !verifyPassword(senha, usuario.getSenha())) {
            resposta = false;
        }
        return resposta;
    }

    public void atualizar(Usuario usuario) {
        usuarioCadastro.atualizar(usuario);
    }

    public void remover(Long id) {
        usuarioCadastro.remover(id);
    }

    public Usuario buscarPorEmail(String email) {
        // realizar alguma lógica de verificação para o controlador
        //String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        
        return usuarioCadastro.buscarPorEmail(email);
    }

    public Usuario getUsuario(Long id) {
        return usuarioCadastro.get(id);
    }

    private boolean isEmailValido(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(regexPattern);
    }

    public String hashPassword(String senha) {
        String hash = null;
        
        try {
            // Cria o objeto MessageDigest para o algoritmo SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Calcula o hash da senha
            byte[] hashedPassword = md.digest(senha.getBytes(StandardCharsets.UTF_8));

            // Converte o hash para uma representação legível hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedPassword) {
                hexString.append(String.format("%02x", b));
            }
            hash = hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    } 

    public boolean verifyPassword(String senha, String hashedPassword) {
        String hashedAttempt = hashPassword(senha);
        return hashedAttempt.equals(hashedPassword);
    }

    public static void main(String[] args) {
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        String senha = "123456";
        String hash = controladorUsuario.hashPassword(senha);
        System.out.println(senha + "\n" + hash);

        senha = "123456";
        hash = controladorUsuario.hashPassword(senha);
        System.out.println(senha + "\n" + hash);

        senha = "000000";
        hash = controladorUsuario.hashPassword(senha);
        System.out.println(senha + "\n" + hash);

        System.out.println(controladorUsuario.isEmailValido(".aksaf@fdlja."));
        System.out.println(controladorUsuario.isEmailValido("aksaf@fdlja.s"));
    }

    /*
    //Autenticação
    // Método para gerar o token JWT
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // Método para criar um cookie com o token JWT e enviar para o frontend
    public void addTokenToResponse(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/"); // Defina o caminho no qual o cookie será válido, normalmente "/"
        cookie.setHttpOnly(true); // Impede que o cookie seja acessível por scripts no frontend
        cookie.setMaxAge((int) (jwtExpirationMs / 1000)); // Define a duração do cookie (em segundos)
        response.addCookie(cookie);
        System.out.println("Cookie adicionado");
    }
     */

}
