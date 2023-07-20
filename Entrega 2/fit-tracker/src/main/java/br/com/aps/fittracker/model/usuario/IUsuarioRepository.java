package br.com.aps.fittracker.model.usuario;

public interface IUsuarioRepository {
    
    public void inserir(Usuario usuario);
    public void atualizar(Usuario usuario);
    public void remover(Long id);
    
    //public Usuario buscarPorId(Long id);   
    public Usuario buscarPorEmail(String email);

}
