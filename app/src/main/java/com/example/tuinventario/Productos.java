package com.example.tuinventario;


public class Productos {
    public static final String TABLE_NAME="inventario";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MATERIAL = "material";
    public static final String COLUMN_CANTIDAD = "cantidad";



    private int id;
    private String material;
    private String canti;




    // Create table SQL query

    public final static String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_MATERIAL + " TEXT,"
                    + COLUMN_CANTIDAD + " TEXT"

                    + ")";

    public Productos() {
    }

    public Productos(int id, String material, String canti) {
        this.id = id;
        this.material = material;
        this.canti = canti;
    }

    public Productos(String string) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCanti() {
        return canti;
    }

    public void setCanti(String canti) {
        this.canti = canti;
    }
}
