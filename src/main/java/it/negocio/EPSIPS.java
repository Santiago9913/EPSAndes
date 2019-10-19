package it.negocio;

public class EPSIPS implements VOEPSIPS {

    private Long idEps;

    private Long idIps;


    public EPSIPS(Long idEps, Long idIps) {
        this.idIps = idIps;
        this.idEps = idEps;
    }


    @Override
    public Long getIdEps() {
        return idEps;
    }

    public void setIdEps(Long idEps) {
        this.idEps = idEps;
    }

    @Override
    public Long getIdIps() {
        return idIps;
    }

    public void setIdIps(Long idIps) {
        this.idIps = idIps;
    }

    @Override
    public String toString() {
        return "EPSIPS [idIps = " + idIps + ", idEps = " + idEps + "]";
    }
}
