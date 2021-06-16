import java.io.*; 

public class Programa {
    

    public static void main (String args []) throws Exception
    {
        // Variaveis:
        BufferedReader leitorDeArquivos = null;
        Matriz labirinto = null;
        Pilha<Coordenada> caminho = null;
        Pilha<Coordenada> caminhoInverso;
        Pilha<Fila<Coordenada>> possibilidades = null;
        Coordenada atual = null;
        Fila<Coordenada> fila = null;
        int linhas=0, colunas=0;
        
        //Definir qual labirinto será usado:
        System.out.println("Digite o nome do arquivo que voce deseja abrir\n");
        String arquivoLabirinto = Teclado.getUmString();
        
        try {
            // Cria o leitor de arquivos e define as linhas e colunas:
            leitorDeArquivos = new BufferedReader(new FileReader(arquivoLabirinto));
            linhas = Integer.parseInt(leitorDeArquivos.readLine());
            colunas = Integer.parseInt(leitorDeArquivos.readLine());

            // Instanciar a matriz labirinto:
            labirinto = new Matriz(linhas,colunas);

            // Adicionar caracteres na Matriz
            int l = 0;
            while (l < linhas)
            {
                for(int c = 0; c < colunas; c++)
                {
                    labirinto.set((char)leitorDeArquivos.read(),l,c);
                }
                l++;
                leitorDeArquivos.readLine();
            }
            
            // Fechar leitor de arquivos:
            leitorDeArquivos.close();
            
        }
        catch(IOException err)
        {
            System.err.println(err.getMessage());
        }

        // Instancia caminho e possibilidades:
        try
        {
            caminho = new Pilha<Coordenada>();
            possibilidades = new Pilha<Fila<Coordenada>>();
        }
        catch (Exception err) {}

        // Repetição para achar a entrada do labirinto:
        acharE: for (int l = 0; l < linhas; l++)
        {
            for(int c = 0; c < colunas; c++)
            {
                // IF para se encontrar a entrada, instaciar atual e sair do repetidor:
                if(labirinto.get(l, c) == 'E')
                {
                    atual = new Coordenada(l,c);
                    break acharE;
                }
            }
        }
        if(atual == null)
            throw new Exception("Nao ha entrada no labirinto");

        boolean instancia = true;
        // Loop principal:
        loop: for(;;)
        {
            // Modo progressivo:
            for(;;)
            {
                if(instancia)
                    fila = new Fila<Coordenada>();

                try{
                    if(labirinto.get(atual.getLinha(), atual.getColuna() + 1) == ' ' || labirinto.get(atual.getLinha(), atual.getColuna() + 1) == 'S')
                        fila.guardeUmItem(new Coordenada(atual.getLinha(), atual.getColuna()+1));
                } catch(Exception err){}

                try{
                    if(labirinto.get(atual.getLinha(), atual.getColuna() - 1) == ' ' || labirinto.get(atual.getLinha(), atual.getColuna()- 1)  == 'S')
                        fila.guardeUmItem(new Coordenada(atual.getLinha(), atual.getColuna()-1));
                } catch(Exception err){}

                try{
                    if(labirinto.get(atual.getLinha() + 1, atual.getColuna()) == ' ' || labirinto.get(atual.getLinha() + 1, atual.getColuna()) == 'S')
                        fila.guardeUmItem(new Coordenada(atual.getLinha()+1, atual.getColuna()));
                } catch(Exception err){}
                
                try{
                    if(labirinto.get(atual.getLinha() - 1, atual.getColuna()) == ' ' || labirinto.get(atual.getLinha() - 1, atual.getColuna()) == 'S')
                        fila.guardeUmItem(new Coordenada(atual.getLinha()-1, atual.getColuna()));
                } catch(Exception err){}
                

                if(!fila.isVazia())
                {
                    atual = fila.recupereUmItem();
                    fila.removaUmItem();
                    
                    if(labirinto.get(atual.getLinha(), atual.getColuna()) == 'S')
                    {
                        System.out.println("Achou");
                        break loop;
                    }

                    labirinto.set('*', atual.getLinha(), atual.getColuna());
                    
                    caminho.guardeUmItem(atual);
                    possibilidades.guardeUmItem(fila);

                    instancia = true;
                    
                }
                else
                    break;  
            }

            //Modo Regressivo:
            do{

                atual = caminho.recupereUmItem();
                caminho.removaUmItem();
                labirinto.set(' ', atual.getLinha(), atual.getColuna());

                fila = possibilidades.recupereUmItem();
                possibilidades.removaUmItem();

                instancia = false;
            }while(fila.isVazia());

        }

        System.out.println(labirinto);
        

        // Printar o Caminho:
        caminhoInverso = new Pilha<Coordenada>();

        while(!caminho.isVazia())
        {
            try
            {
                caminhoInverso.guardeUmItem(caminho.recupereUmItem());
                caminho.removaUmItem();
            }
            catch(Exception err){}
        }
        
        System.out.println("O caminho é: ");

        while(!caminhoInverso.isVazia())
        {
            try
            {
                System.out.print(caminhoInverso.recupereUmItem());
                caminhoInverso.removaUmItem();
                System.out.print(" - ");
            }
            catch(Exception err) {}
        }

        
    }
}
