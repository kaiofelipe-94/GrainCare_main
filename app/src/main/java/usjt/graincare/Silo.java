package usjt.graincare;

import java.io.Serializable;

/**
 * Created by kaio on 14/05/2016.
 */
public class Silo implements Comparable<Silo> ,Serializable
{
    private int id;
    private int idArduino;
    private String nomeSilo;
    private String tipoGrao;
    private double capacidadeSilo;
    private String dataFechamento;
    private String dataAbertura;
    private String Localizacao;

    public Silo(int id, int idArduino, String nomeSilo, String tipoGrao,
                double capacidadeSilo, String dataFechamento, String dataAbertura,
                String localizacao)
    {
        super();
        this.id = id;
        this.idArduino = idArduino;
        this.nomeSilo = nomeSilo;
        this.tipoGrao = tipoGrao;
        this.capacidadeSilo = capacidadeSilo;
        this.dataFechamento = dataFechamento;
        this.dataAbertura = dataAbertura;
        Localizacao = localizacao;
    }


    public int getId()
    {
        return id;
    }


    public int getIdArduino()
    {
        return idArduino;
    }


    public String getNomeSilo()
    {
        return nomeSilo;
    }


    public String getTipoGrao()
    {
        return tipoGrao;
    }


    public double getCapacidadeSilo()
    {
        return capacidadeSilo;
    }


    public String getDataFechamento()
    {
        return dataFechamento;
    }


    public String getDataAbertura()
    {
        return dataAbertura;
    }


    public String getLocalizacao()
    {
        return Localizacao;
    }


    public void setId(int id)
    {
        this.id = id;
    }


    public void setIdArduino(int idArduino)
    {
        this.idArduino = idArduino;
    }


    public void setNomeSilo(String nomeSilo)
    {
        this.nomeSilo = nomeSilo;
    }


    public void setTipoGrao(String tipoGrao)
    {
        this.tipoGrao = tipoGrao;
    }


    public void setCapacidadeSilo(double capacidadeSilo)
    {
        this.capacidadeSilo = capacidadeSilo;
    }


    public void setDataFechamento(String dataFechamento)
    {
        this.dataFechamento = dataFechamento;
    }


    public void setDataAbertura(String dataAbertura)
    {
        this.dataAbertura = dataAbertura;
    }


    public void setLocalizacao(String localizacao)
    {
        Localizacao = localizacao;
    }

    @Override
    public int compareTo(Silo arg0)
    {
        return 0;
    }
}

