public class Revista extends Publicacao {

    public String periodicidade;
    public Revista(String nome, String assunto, String edicao, Editora editora, String peridiocidade)
    {
        super(nome, assunto, edicao, editora);
        this.periodicidade = peridiocidade;
    }
}
