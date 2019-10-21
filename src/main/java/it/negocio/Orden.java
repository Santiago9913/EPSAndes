package it.negocio;

public class Orden implements VOOrden {

    private long id;

    private String desc;

    public Orden() {
        id = 0;
        desc = "";
    }

    public Orden(String desc, long id) {
        this.desc = desc;
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public String getDescripcion() {
        return desc;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Orden [id=" + id + ", Descripcion=" + desc + "]";
    }

}
