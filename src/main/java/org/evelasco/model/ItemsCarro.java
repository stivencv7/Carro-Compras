package org.evelasco.model;

import org.evelasco.model.entity.Producto;

import java.util.Objects;

public class ItemsCarro {

    private int cantidad;
    private Producto producto;

    public ItemsCarro() {
    }

    public ItemsCarro(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getImporte(){
        return producto.getPrecio()*cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsCarro that = (ItemsCarro) o;
        return Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto);
    }
}
