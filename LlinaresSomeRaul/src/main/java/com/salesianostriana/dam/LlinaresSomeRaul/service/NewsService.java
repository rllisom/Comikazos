package com.salesianostriana.dam.LlinaresSomeRaul.service;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;
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
        return findAll().stream()
                .sorted(Comparator.comparing(News::getDateNew).reversed())
                .toList();
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

    //EDIT
    public boolean editNews(Long id, News news){
        News original = findById(id);
        news.setId(id);
        boolean exist = findAll().stream()
                .anyMatch(n->n.getDateNew().equals(news.getDateNew()) &&
                        n.getImgNew().equalsIgnoreCase(news.getImgNew()) &&
                        n.getTextImg().equalsIgnoreCase(news.getTextImg()) &&
                        n.getTitle().equalsIgnoreCase(news.getDescription()));

        if(original.equals(news)){
            return false;
        }
        if(exist){
            return false;
        }
        edit(news);
        return true;
    }

    //ADD
    public News addNews (News news){
        boolean exist = findAll().stream()
                .anyMatch(n->n.getDateNew().equals(news.getDateNew()) &&
                        n.getImgNew().equalsIgnoreCase(news.getImgNew()) &&
                        n.getTextImg().equalsIgnoreCase(news.getTextImg()) &&
                        n.getTitle().equalsIgnoreCase(news.getDescription()));

        if (exist) {
            return null;
        } else {
            return save(news);
        }
    }
}
