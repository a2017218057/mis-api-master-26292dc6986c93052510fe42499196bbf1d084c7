package cn.edu.tju.dao;


import antlr.PythonCharFormatter;
import cn.edu.tju.model.LoadInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.boot.autoconfigure.elasticsearch.jest.JestProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.swing.text.StyledEditorKit;
import java.util.List;

public interface LoadInfoRepo extends CrudRepository<LoadInfo, Integer>, PagingAndSortingRepository<LoadInfo, Integer> ,JpaRepository<LoadInfo,Integer> {

    public List<LoadInfo> findByIdOrderByLoadtimeDesc(String ID, Pageable pageable);
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
    /*查询用户自己设置不可见的文件*/
    public List<LoadInfo> findByIdAndNameContainingAndTagContainingOrNameContainingAndTagContainingAndIfcheck(String username,String name,String tag,String name1,String tag1,Boolean c,Pageable pageable);
    public List<LoadInfo> findByIdAndNameContainingOrNameContainingAndIfcheck(String username,String name,String name1,Boolean c,Pageable pageable);
    public List<LoadInfo> findByIdAndTagContainingOrTagContainingAndIfcheck(String username,String tag,String tag1,Boolean c,Pageable pageable);
    public long countByIdAndNameContainingAndTagContainingOrNameContainingAndTagContainingAndIfcheck(String username, String name, String tag, String name1, String tag1,Boolean c);
    public long countByIdAndNameContainingOrNameContainingAndIfcheck(String username, String name, String name1, Boolean c);
    public long countByIdAndTagContainingOrTagContainingAndIfcheck(String username, String tag, String tag1, Boolean c);
    public long countByifcheck(Boolean c);

    /*统计页面*/
    public long countByIfcheckdown(Boolean c);
    public long countByIfcheck(Boolean c);
}
