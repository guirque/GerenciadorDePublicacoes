import java.io.*;

public class Publicacao implements Serializable {
    public String nome;
    public String assunto;
    public String edicao;
    public Editora editora;
    public String[] autores;
    private static final long serialVersionUID = 1L; 
    //Atencao: serialVersionUID caracteriza a versao da classe. Esse valor deve ser atualizado manualmente, a fim de
    //evitar erros de serializacao/desserializacao.

    public Publicacao(String nome, String assunto, String edicao, Editora editora)
    {
        this.nome = nome;
        this.assunto = assunto;
        this.edicao = edicao;
        this.editora = editora;
        this.editora.numPublicacoes++;
    }

    /**
     * <p>Salva uma publicação em um arquivo binário com caminho especificado.</p>
     * <p>Se o arquivo não existir, ele é criado.</p>
     * @param caminho O caminho para o arquivo binário que será usado para o armazenamento.
     */
    public void salvar(String caminho)
    {
        File file = new File(caminho); //Encontrando arquivo
        try
        {
            if(!file.exists()) file.createNewFile(); //Criando arquivo, caso ele nao exista.

            //FileOutputStream: direcionar a saida para um arquivo.
            //Criando stream de objeto a partir de uma de arquivo e escrevendo instancia nesse arquivo.
            FileOutputStream outputToFile = new FileOutputStream(file, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputToFile);

            //Fechando streams
            objectOutputStream.writeObject(this);
            outputToFile.close();
        }
        catch(FileNotFoundException error)
        {
            System.out.println("<!> Arquivo não encontrado");
        }
        catch(IOException error)
        {
            System.out.println("<!> " + error);
        }
    }

    /**
     * Retorna uma publicação armazenada em um arquivo binário.
     * @param caminho O caminho do arquivo de que importar a publicação.
     */
    public static Publicacao importarPub(String caminho)
    {
        Publicacao resultado = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(caminho);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            resultado = (Publicacao) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        }
        catch(FileNotFoundException error)
        {
            System.out.println("<!> Arquivo nao encontrado");
        }
        catch(IOException error)
        {
            System.out.println("<!> " + error);
        }
        catch(ClassNotFoundException error)
        {
            System.out.println("<!> Publicacao nao encontrada em arquivo");
        }
        return resultado;
    }
}
