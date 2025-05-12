package com.salesianostriana.dam.LlinaresSomeRaul.service;

import com.salesianostriana.dam.LlinaresSomeRaul.model.News;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.NewsRepository;
import com.salesianostriana.dam.LlinaresSomeRaul.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NewsService extends BaseService<News,Long, NewsRepository> {

    //GET ALL
    public List<News> getAll(){
        return findAll();
    }
    //GET 3 NEWS
    public List<News> getSomeNews(){
        return findAll().stream()
                .sorted(Comparator.comparing(News::getDateNew).reversed())
                .limit(3)
                .toList();
    }

    //DELETE
    public boolean deleteNew(Long id){
        if (findById(id) != null) {
            delete(findById(id));
            return true;
        }else {
            return false;
        }
    }
}
