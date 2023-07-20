import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.io.File;

public class GUI implements ActionListener
{
    private static final int TAM = 1000;

    //Storage
    int editorasI = 0;
    Editora[] editoras = new Editora[TAM];
    int publicacoesI = 0;
    Publicacao[] publicacoes = new Publicacao[TAM];

    JFrame window;
    JTextField differentField;
    JLabel differentFieldLabel;
    JFileChooser fileChooser;

    //Insert Publicacao
    JTextField nome;
    JTextField assunto;
    JTextField edicao;
    JList<String> editorasJList;
    ButtonGroup option;
    JRadioButton revista;
    JRadioButton livro;

    //Center Panel
    JPanel centerPanel = new JPanel();

    //Editora
    JTextField nomeEditoraTexto;
    JTextField websiteEditoraTexto;
    JList<String> editorasVisiveis;
    DefaultListModel<String> modelEditoras = new DefaultListModel<String>(); //Allows us to update list visually and directly

    JLabel infoEditora;

    //Saving
    JTextField savePath;

    //Publicacoes
    JList<String> publicacoesVisiveis;
    DefaultListModel<String> modelPublicacoes = new DefaultListModel<String>(); //Allows us to update list visually and directly
    JLabel infoPublicacoes;

    //Error
    JLabel error;

    //GUI Setup
    public GUI()
    {
        //Setting frame
        window = new JFrame("Gerenciador de Publicações");
        window.pack();
        window.setSize(new Dimension(900, 600));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Setting Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //Setting Title
        JLabel title = new JLabel("Gerenciador de Publicações");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(title, BorderLayout.NORTH);

        //Setting Forms ----------------------------------------------------------------
        Color defaultColor = new Color(220, 220, 220);
        JPanel formsBox = new JPanel();
        formsBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel forms = new JPanel();
        Dimension minimumFormsSize = new Dimension(500, 0);
        forms.setLayout(new GridBagLayout());
        formsBox.setBackground(defaultColor);
        formsBox.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(30, 30, 30)));


        JLabel insertLabel = new JLabel("Criar Nova Publicação");
        insertLabel.setHorizontalAlignment(SwingConstants.CENTER);

        option = new ButtonGroup();
        revista = new JRadioButton("Revista", true);
        livro = new JRadioButton("Livro", false);
        revista.addActionListener(this);
        revista.setActionCommand("SRevista");
        livro.addActionListener(this);
        livro.setActionCommand("SLivro");

        option.add(revista);
        option.add(livro);

        nome = new JTextField("Um nome");

        assunto = new JTextField("Um assunto");
        assunto.setMinimumSize(minimumFormsSize);

        edicao = new JTextField("Uma edição");

        editorasJList = new JList<String>(modelEditoras);

        JButton submit = new JButton("Insert");

        GridBagConstraints insertGrid = new GridBagConstraints();
        insertGrid.gridy = 0; insertGrid.gridwidth = 2;
        forms.add(insertLabel, insertGrid);

        GridBagConstraints textFieldGrid = new GridBagConstraints();
        textFieldGrid.fill = GridBagConstraints.HORIZONTAL;
        
        GridBagConstraints optionConstraints = new GridBagConstraints();
        optionConstraints.gridx = 0; optionConstraints.gridy = 2;
        forms.add(revista, optionConstraints);

        optionConstraints.gridx = 1;
        forms.add(livro, optionConstraints);
        
        textFieldGrid.gridx = 0; textFieldGrid.gridy = 1;
        forms.add(new JLabel("Nome: "), textFieldGrid, 0);
        textFieldGrid.gridx = 1;
        forms.add(nome, textFieldGrid); 

        textFieldGrid.gridx = 0;
        textFieldGrid.gridy = 3;
        forms.add(new JLabel("Assunto: "), textFieldGrid, 0);
        textFieldGrid.gridx = 1;
        forms.add(assunto, textFieldGrid); 

        textFieldGrid.gridx = 0;
        textFieldGrid.gridy = 4;
        forms.add(new JLabel("Edição: "), textFieldGrid, 0);
        textFieldGrid.gridx = 1;
        forms.add(edicao, textFieldGrid);
        
        textFieldGrid.gridx = 0;
        textFieldGrid.gridy = 5;
        forms.add(new JLabel("Editoras: "), textFieldGrid, 0);
        textFieldGrid.gridx = 1;
        forms.add(editorasJList, textFieldGrid);

        textFieldGrid.gridx = 0;
        textFieldGrid.gridy = 6;
        differentFieldLabel= new JLabel("Peridiocidade: ");
        forms.add(differentFieldLabel, textFieldGrid, 0);
        differentField = new JTextField();
        textFieldGrid.gridx = 1;
        forms.add(differentField, textFieldGrid);

        JPanel salvarPanel = new JPanel();
        salvarPanel.setLayout(new BoxLayout(salvarPanel, 1));
        JSeparator separatorS = new JSeparator(JSeparator.HORIZONTAL);
        JLabel tituloSalvar = new JLabel("Salvar Em Arquivo");
        tituloSalvar.setHorizontalAlignment(SwingConstants.CENTER);
        JButton saveButton = new JButton("Salvar");
        saveButton.addActionListener(this);
        saveButton.setActionCommand("salvarPub");
        JLabel caminhoSalvar = new JLabel("Caminho (sem aspas): ");
        savePath = new JTextField();

        salvarPanel.add(separatorS);
        salvarPanel.add(tituloSalvar);
        salvarPanel.add(caminhoSalvar);
        salvarPanel.add(savePath);
        salvarPanel.add(saveButton);
        salvarPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        textFieldGrid.gridx = 0;
        textFieldGrid.gridy = 7;
        textFieldGrid.gridwidth = 2;

        submit.setHorizontalAlignment(SwingConstants.CENTER);
        submit.addActionListener(this);
        submit.setActionCommand("Submit");
        forms.add(submit, textFieldGrid);

        textFieldGrid.gridx = 0;
        textFieldGrid.gridy = 8;
        forms.add(salvarPanel, textFieldGrid);

        //Left Panel ---------------------------------------------------------
        JPanel leftPanelBox = new JPanel();
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, 1));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leftPanelBox.setBackground(defaultColor);

        leftPanelBox.add(leftPanel);

        //Setting Import (Panel) ----------------------------------------------
        JPanel importPanel = new JPanel();
        importPanel.setLayout(new BoxLayout(importPanel, 1));
        JLabel importLabel = new JLabel("Importar Publicação");
        importPanel.add(importLabel);
        
        JLabel chooseFileLabel = new JLabel("Escolha um Arquivo");
        JButton chooseFileButton = new JButton("Escolher");
        JButton importFileButton = new JButton("Importar Publicação");
        chooseFileButton.addActionListener(this);
        chooseFileButton.setActionCommand("Import");
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        importPanel.add(chooseFileLabel);
        importPanel.add(chooseFileButton);
        importFileButton.addActionListener(this);
        importFileButton.setActionCommand("importPub");
        importPanel.add(importFileButton);
        importPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(0, 0, 0)));
        leftPanel.add(importPanel);
        //importPanel.add(fileChooser);

        //Creating Publisher --------------------------------------------------
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        JLabel tituloEditora = new JLabel("Criar Editora");
        tituloEditora.setHorizontalAlignment(SwingConstants.CENTER);
        tituloEditora.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
        importPanel.add(separator);
        leftPanel.add(tituloEditora);

        importPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
        JPanel editoraPanel = new JPanel();
        editoraPanel.setLayout(new BoxLayout(editoraPanel, 1));
        //nome, website
        JLabel nomeEditoraLabel = new JLabel("Nome: ");
        nomeEditoraTexto = new JTextField();
        editoraPanel.add(nomeEditoraLabel); editoraPanel.add(nomeEditoraTexto);
        JLabel websiteEditoraLabel = new JLabel("Website: ");
        websiteEditoraTexto = new JTextField();
        editoraPanel.add(websiteEditoraLabel); editoraPanel.add(websiteEditoraTexto);
        JButton submitEditora = new JButton("Submit");
        submitEditora.addActionListener(this);
        submitEditora.setActionCommand("SEditora");
        editoraPanel.add(submitEditora);

        leftPanel.add(editoraPanel);

        JPanel errorMsgs = new JPanel();
        errorMsgs.setLayout(new BoxLayout(errorMsgs, 1));
        JSeparator separator3 = new JSeparator(0);
        JLabel errorTitle = new JLabel("Erros"); 
        errorTitle.setHorizontalAlignment(SwingConstants.CENTER);
        error = new JLabel("<html>Nenhum erro encontrado até o momento.</html>");
        errorMsgs.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        errorMsgs.add(separator3); 
        errorMsgs.add(errorTitle);
        errorMsgs.add(error);

        leftPanel.add(errorMsgs);

        //Center Panels ------------------------------------------------------
        JPanel centerPanelBox = new JPanel();
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, 1));

        //Editora
        JPanel editorasCenterPanel = new JPanel();
        editorasCenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 1));
        JLabel editorasVisiveisLabel = new JLabel("Editoras");
        editorasVisiveis = new JList<String>(modelEditoras);
        infoEditora = new JLabel("Selecione uma Editora Para Obter Informações sobre Ela.");
        editorasVisiveis.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event)
            {
                int editorasIndex = editorasVisiveis.getSelectedIndex();
                if(editorasIndex != -1){
                Editora valueLabel = editoras[editorasIndex];
                infoEditora.setText("<html>Nome: " + valueLabel.nome + "<br/>Website: \n" + valueLabel.website + "<br/>Num. de Pub.: " + valueLabel.numPublicacoes + "</html>");}
            }
        });
        editorasCenterPanel.add(editorasVisiveisLabel);
        editorasCenterPanel.add(editorasVisiveis);
        editorasCenterPanel.add(infoEditora);
        editorasCenterPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 0)));

        centerPanel.setBackground(defaultColor);
        centerPanel.add(editorasCenterPanel);
        centerPanelBox.add(centerPanel);

        //Publicacoes
        JPanel publicacoesCenterPanel = new JPanel();
        publicacoesCenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 1));
        JLabel publicacoesVisiveisLabel = new JLabel("Publicações");
        publicacoesVisiveis = new JList<String>(modelPublicacoes);
        infoPublicacoes = new JLabel("Selecione uma Publicação Para Obter Informações sobre Ela.");
        publicacoesCenterPanel.add(publicacoesVisiveisLabel);
        publicacoesCenterPanel.add(publicacoesVisiveis);
        publicacoesCenterPanel.add(infoPublicacoes);
        publicacoesVisiveis.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event)
            {
                try{
                int pubIndex = publicacoesVisiveis.getSelectedIndex();
                if(pubIndex!= -1 && publicacoes[pubIndex].getClass().equals(Livro.class)){
                    Livro valueLabel = (Livro) publicacoes[pubIndex];
                    String autoresLabel = "";
                    for(String umaString : valueLabel.autores)
                    {
                        autoresLabel += "<br/>   - " + umaString;
                    }
                    infoPublicacoes.setText("<html>Nome: " + valueLabel.nome + "<br/>Edição: \n" + valueLabel.edicao + "<br/>Assunto: " + valueLabel.assunto + "<br/>Editora: " + valueLabel.editora.nome +"<br/>Autores: " + autoresLabel + "</html>");
                }
                else if(pubIndex != -1)
                {
                    Revista valueLabel = (Revista) publicacoes[pubIndex];
                    infoPublicacoes.setText("<html>Nome: " + valueLabel.nome + "<br/>Edição: \n" + valueLabel.edicao + "<br/>Assunto: " + valueLabel.assunto + "<br/>Editora: " + valueLabel.editora.nome +"<br/>Peridiocidade: " + valueLabel.periodicidade + "</html>");
                }
            }catch(Exception o)
            {
                System.out.println("<!> Error displaying Publicacoes: " + o);
            }
            }
        });
        publicacoesCenterPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 0)));
        centerPanel.add(publicacoesCenterPanel);

        //Integrating Panels with Frame ---------------------------------------
        formsBox.add(forms);
        mainPanel.add(formsBox, BorderLayout.EAST);
        mainPanel.add(leftPanelBox,BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        window.add(mainPanel);

        //Visibility
        window.setVisible(true);
    }

    //Events
    public void actionPerformed(ActionEvent event)
    {
        switch(event.getActionCommand())
        {
            case "SRevista":
                differentFieldLabel.setText("Peridiocidade: ");
                break;
            case "SLivro":
                differentFieldLabel.setText("Autores: ");
                break;
            case "Submit":
                Publicacao novo;
                try{
                int indexEditora = editorasJList.getSelectedIndex();
                if(indexEditora != -1){
                    if(option.isSelected(revista.getModel())) novo = new Revista(nome.getText(), assunto.getText(), edicao.getText(), editoras[indexEditora], differentField.getText());
                    else{ 
                        String[] novaString = differentField.getText().split(",");
                        novo = new Livro(nome.getText(), assunto.getText(), edicao.getText(), editoras[editorasJList.getSelectedIndex()], novaString);
                    }
                    publicacoes[publicacoesI] = novo;
                    modelPublicacoes.add(publicacoesI, novo.nome);
                    publicacoesI++;
                }}
                catch(Exception o)
                {
                    error.setText("<html><p>Error ao inserir nova publicação.</p> <p>Certifique-se que todos os campos estão preenchidos.</p></html>");
                    System.out.println("<!> Error with submission: " + o);
                }
                break;
            case "Import":
                fileChooser.showOpenDialog(window);
                break;
            case "importPub":
                try{
                    File importMe = fileChooser.getSelectedFile();
                    if(importMe.exists()){
                        Publicacao pubImportada = Publicacao.importarPub(importMe.getAbsolutePath());
                        publicacoes[publicacoesI] = pubImportada;
                        modelPublicacoes.add(publicacoesI, pubImportada.nome);
                        publicacoesI++;
                    }
                }
                catch(Exception o)
                {
                    error.setText("<html><p>Error ao importar publicação.</p> <p>Certifique-se que o arquivo escolhido é o correto.</p></html>");
                    System.out.println("<!> Error with importing file: " + o);
                }
                break;
            case "salvarPub":
                Publicacao novaPub;
                if(editorasJList.getSelectedIndex() != -1){
                    if(option.isSelected(revista.getModel()))
                    {
                        novaPub = new Revista(nome.getText(), assunto.getText(), edicao.getText(), editoras[editorasJList.getSelectedIndex()], differentField.getText());
                    }
                    else
                    {
                        novaPub = new Livro(nome.getText(), assunto.getText(), edicao.getText(), editoras[editorasJList.getSelectedIndex()], differentField.getText().split(","));
                    }
                    novaPub.salvar(savePath.getText());
                }
                        break;
            case "SEditora":

                String nomeEditora = nomeEditoraTexto.getText();
                String websiteEditora = websiteEditoraTexto.getText();
                if(nomeEditora.length() >= 1 && websiteEditora.length() >= 1)
                {
                    editoras[editorasI] = new Editora(nomeEditora, websiteEditora);
                    modelEditoras.add(editorasI, nomeEditora);
                    editorasI++;
                }
                
                break;
        }   
    }
}