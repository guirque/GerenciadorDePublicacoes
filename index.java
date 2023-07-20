public class index
{
    public static void main(String args[])
    {
        new GUI();
        System.out.println("Hello, World!");
        
        
        
        /*
        Editora a = new Editora("a", "aaaa");
        Revista umaRevista = new Revista("Hey", "Nada", "p1", a, "mensalmente");
        
        //umaRevista.salvar("armazenamento.bin");
        Revista resgatada = (Revista) Publicacao.importarPub("armazenamento.bin");
        System.out.println(resgatada.edicao);

        //umaRevista.salvar("a.bin");
        Revista b = (Revista) Publicacao.importarPub("a.bin");
        System.out.println(b.editora.numPublicacoes);
        */

        // Parte nova (Questão F)
        Editora c = new Editora("HTML5 Pocket", "http://shop.oreilly.com/0636920029274.do");
        Publicacao ca = new Publicacao ("HTML5 Pocket", "Estudos HTML5", "5th Edition", c, "Jennifer Robbis");
        System.out.println(Editora.numPublicacoes);
    }
}
