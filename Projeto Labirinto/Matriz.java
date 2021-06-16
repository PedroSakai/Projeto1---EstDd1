public class Matriz
{
    
    // Atributos:
    private char[][] tabela;     // Matriz de Char
    private int linhas, colunas; // INT's para a linha e coluna na matriz


    // Construtor:
    public Matriz(int l, int c)
    {
        // Define a quantidade de linhas e colunas, e instancia a Matriz:
        this.linhas = l;
        this.colunas = c;
        tabela = new char[l][c];
    }

    // Getters:
    public int getLinhas()
    {
        return linhas;  // Retorna a quantidade de linhas na matriz
    }

    public int getColunas()
    {
        return colunas; // Retorna a quantidade de linhas na matriz
    }

    public char get(int l, int c)
    {
        //Pega o char de uma posição especifica e o retorna:
        char ret = tabela[l][c]; 
        return ret;
    }


    // Setter:
    public void set(char x, int l, int c)
    {
        tabela[l][c] = x;   // Define o valor x na linha l e coluna c da matriz
    }


    // Metodo Funcional
    public void remove(int l, int c)
    {
        tabela[l][c] = 0;
    }


    // Metodos Obrigatorios:
    public String toString()
    {
        String ret = "";
        
        for(int l = 0; l < this.linhas; l++)
        {
            for(int c = 0; c < this.colunas; c++)
            {
                ret +=  tabela[l][c];
            }
            ret += "\n";
        }
        return ret;
    }

    @Override
    public int hashCode()
    {
        int ret = 666;

        ret = ret * 7 + new Integer(this.linhas).hashCode();
        ret = ret * 7 + new Integer(this.colunas).hashCode();

        for (int l = 0; l < linhas; l++)
        {
            for(int c = 0; c < colunas; c++)
            {
                ret = ret * 7 + new Integer(tabela[l][c]).hashCode();
            }
        }   

        if (ret<0)
            ret=-ret;

        return ret;
    }

    @Override
    public boolean equals(Object obj)
    {
        if( this == obj ) 
            return true;
    
        if( obj == null )
            return false;
            
        if( obj.getClass() != Matriz.class )
            return false;
            
        if( this.linhas != ((Matriz)obj).linhas )
            return false;

        if( this.colunas != ((Matriz)obj).colunas )
            return false;
        
        if( this.tabela != ((Matriz)obj).tabela)
            return false;
            
        return true;
    }

    public Matriz( Matriz modelo ) throws Exception
    {
        if(modelo == null)
            throw new Exception("Modelo ausente");

        this.linhas = modelo.linhas;
        this.colunas = modelo.colunas;
        
        for (int l = 0; l < linhas; l++)
        {
            for(int c = 0; c < colunas; c++)
            {
                this.tabela[l][c] = modelo.tabela[l][c];
            }
        }   
    }

    public Object clone()
    {
        Matriz ret = null;

        try
        {
            ret = new Matriz(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }


}