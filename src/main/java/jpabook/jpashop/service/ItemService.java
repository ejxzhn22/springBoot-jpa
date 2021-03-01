package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }


    /*
    * 변경감지기능 사용
    * 영속성 컨텍스트에서 다시 조회한후에 데이터를 수정하는 방법
    * 트랜잭션 안에서 엔티티를 다시 조회,변경할 값 선택-> 트랜잭션 커밋 시점에 변경감지(Dirty Checking)이 동작해서
    * 데이터 베이스에 update sql 실행
    * */
    @Transactional
    public void updateItem(Long itemId,String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
//        findItem.change (price,name,stockQuantity);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long ItemId) {
        return itemRepository.findOne(ItemId);
    }
}
