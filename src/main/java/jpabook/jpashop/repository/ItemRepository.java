package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    /* id가 없으면 신규로 보고 persist() 실행
    * id가 있으면 이미 데이터베이스에 저장된 엔티티를 수정한다고 보고, merget()를 실행
    * */
    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        }else {
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    //여러 개 찾는 거는 jpql 작성해야 함
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
