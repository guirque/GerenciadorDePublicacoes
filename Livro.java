public class Livro extends Publicacao {
    public String[] autores;
    public Livro(String nome, String assunto, String edicao, Editora editora, String[] autores)
    {
        super(nome, assunto, edicao, editora);
        this.autores = autores;
    }
}
