package cn.edu.tju.dao;


import cn.edu.tju.model.LoadInfo;
import org.springframework.boot.autoconfigure.elasticsearch.jest.JestProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LoadInfoRepo extends CrudRepository<LoadInfo, Integer>, PagingAndSortingRepository<LoadInfo, Integer> ,JpaRepository<LoadInfo,Integer> {

    public List<LoadInfo> findById(String ID, Pageable pageable);
    public List<LoadInfo> findByifcheckOrderByLoadtimeDesc(Boolean c, Pageable pageable);
    public int countById(String ID);
    //@Query("select o from LoadInfo o where u.username like %?1%")
    public List<LoadInfo> findByNameContainingAndIfcheck(String name,Boolean c,Pageable pageable);
    public List<LoadInfo> findByTagContainingAndIfcheck(String tag,Boolean c,Pageable pageable);
    public List<LoadInfo> findByNameContainingAndTagContainingAndIfcheck(String name,String tag,Boolean c,Pageable pageable);
    public int countByNameLike(String event);
    public int countByTagLike(String tag);
    public int countByNameLikeAndTagLike(String name,String tag);
    public long countByNameContainingAndTagContainingAndIfcheck(String name,String tag,Boolean c);
    public long countByNameContainingAndIfcheck(String name,Boolean c);
    public long countByTagContainingAndIfcheck(String tag,Boolean c);
}
