public class Revista extends Publicacao {

    private String periodicidade;
    public Revista(String nome, String assunto, String edicao, Editora editora, String[] autores, String peridiocidade)
    {
        super(nome, assunto, edicao, editora, autores);
        this.periodicidade = peridiocidade;
    }
}
