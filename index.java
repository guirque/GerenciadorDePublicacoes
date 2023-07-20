public class index
{
    public static void main(String args[])
    {
        //Construcao do GUI
        new GUI();

        // Parte nova (Questão F)
        String [] vet = {"Jennifer Robbis"};
        Editora c = new Editora("HTML5 Pocket", "http://shop.oreilly.com");
        Livro vg = new Livro ("HTML5 Pocket", "Estudos HTML5", "5th Edition", c, vet);
        System.out.println(c.numPublicacoes);
    }
}
