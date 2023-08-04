package org.evelasco.model;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@SessionScoped
@Named
public class Carro implements Serializable {
    private List<ItemsCarro> items;

    @Inject
    private transient Logger log;

    @PostConstruct
    public void inicializar(){
        items=new ArrayList<>();
        log.info("inicializando Carro de compras");
    }

    @PreDestroy
    private void destruirCarro(){
        log.info("destruyendo el carro de compras");
    }

    public List<ItemsCarro> getItems() {
        return items;
    }

    public void addItem(ItemsCarro item){
        Optional<ItemsCarro> ite=items.stream()
                .filter(i->item.getProducto().getId().equals(i.getProducto().getId()))
                .findAny();
        if(ite.isPresent()){
            ItemsCarro i=ite.get();
            i.setCantidad(i.getCantidad()+1);
        }else{
            items.add(item);
        }
    }

    public int getTotal(){
        return items.stream().mapToInt(ItemsCarro::getImporte).sum();
    }

    public void removeItem(List<String> ids) {
        if(!ids.isEmpty()){
            ids.forEach(this::removeFound);
        }
    }

    private void removeFound(String id) {
        Optional<ItemsCarro>ite=found(id);
        if(ite.isPresent()){
            System.out.println("elimino");
            items.remove(ite.get());
        }
    }

    private Optional<ItemsCarro> found(String id) {
        return items.stream()
                .filter(i->id.equals(String.valueOf(i.getProducto().getId())))
                .findAny();

    }

    public void updateCantidad(String id, String cantidad) {
        Optional<ItemsCarro>ite=items.stream()
                .filter(i->id.equals(String.valueOf(i.getProducto().getId())))
                .findAny();
        ite.ifPresent(i->{
            i.setCantidad(Integer.parseInt(cantidad));
        });
    }
}
