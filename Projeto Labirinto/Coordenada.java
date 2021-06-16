public class Coordenada {
    
    // Atributos:
    int linha, coluna;


    // Construtor:
    public Coordenada(int l, int c)
    {
        this.linha = l;
        this.coluna = c;
    }


    // Getters:
    public int getLinha()
    {
        return this.linha;
    }

    public int getColuna()
    {
        return this.coluna;
    }


    // Metodos Obrigatótios:
    public String toString()
    {
        String ret = "[" + this.linha + ", " + this.coluna + "]";
        return ret;
    }

    @Override
    public int hashCode()
    {
        int ret = 666;

        ret = ret * 7 + new Integer(this.linha).hashCode();
        ret = ret * 7 + new Integer(this.coluna).hashCode();

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
            
        if( obj.getClass() != Coordenada.class )
            return false;
            
        if( this.linha != ((Coordenada)obj).linha )
            return false;

        if( this.coluna != ((Coordenada)obj).coluna )
            return false;
            
        return true;
    }

    public Coordenada( Coordenada modelo ) throws Exception
    {
        if(modelo == null)
            throw new Exception("Modelo ausente");

        this.linha = modelo.linha;
        this.coluna = modelo.coluna;
    }

    public Object clone()
    {
        Coordenada ret = null;

        try
        {
            ret = new Coordenada(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }

}
