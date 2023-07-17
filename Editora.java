import java.io.Serializable;

public class Editora implements Serializable
{
    private String nome;
    private String website;
    public int numPublicacoes;
    private static final long serialVersionUID = 2L;
    public Editora(String nome, String website)
    {
        this.nome = nome;
        this.website = website;
        this.numPublicacoes = 0;
    }

    public int getNumPubs()
    {
        return this.numPublicacoes;
    }
}