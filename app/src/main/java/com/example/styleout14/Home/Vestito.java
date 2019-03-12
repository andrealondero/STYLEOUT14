package com.example.styleout14.Home;

public class Vestito {

    private String id;

    private Integer selected;

    private Integer posFatto;

    public Integer getPosFatto() {
        return posFatto;
    }

    public void setPosFatto(Integer posFatto) {
        this.posFatto = posFatto;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String colore;
    private String colorCode;

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getIsDisponibile() {
        return isDisponibile;
    }

    public void setIsDisponibile(String isDisponibile) {
        this.isDisponibile = isDisponibile;
    }

    private String isDisponibile;
    private String nome;
    private String tessuto;
    private String tipoVestito;
    private String style;
    private int pic_tag;

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String isDisponibile() {
        return isDisponibile;
    }

    public void setDisponibile(String disponibile) {
        isDisponibile = disponibile;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTessuto() {
        return tessuto;
    }

    public void setTessuto(String tessuto) {
        this.tessuto = tessuto;
    }

    public String getTipoVestito() {
        return tipoVestito;
    }

    public void setTipoVestito(String tipoVestito) {
        this.tipoVestito = tipoVestito;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getPic_tag() {
        return pic_tag;
    }

    public void setPic_tag(int pic_tag) {
        this.pic_tag = pic_tag;
    }
}
