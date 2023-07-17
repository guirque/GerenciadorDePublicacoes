public class index
{
    public static void main(String args[])
    {
        new GUI();
        System.out.println("Hello, World!");

        Editora a = new Editora("a", "aaaa");
        Revista umaRevista = new Revista("Hey", "Nada", "p1", a, new String[2], "mensalmente");
        
        
        //umaRevista.salvar("armazenamento.bin");
        Revista resgatada = (Revista) Publicacao.importarPub("armazenamento.bin");
        System.out.println(resgatada.edicao);

        //umaRevista.salvar("a.bin");
        Revista b = (Revista) Publicacao.importarPub("a.bin");
         System.out.println(b.editora.numPublicacoes);

    }
}