package com.h2.dogrooming.designer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignerService {

    private DesignerRepository designerRepository;

    public DesignerService(DesignerRepository designerRepository){
        this.designerRepository = designerRepository;
    }

    public List<Designer> getDesignerList(){
        return designerRepository.findAll();
    }

    public Slice<Designer> findAll(Pageable pageable){
        return designerRepository.findAllBy(pageable);
    }

    public Slice<Designer> getDesignerList(String keyword, Pageable pageable){
        if(keyword == null || keyword.isEmpty()){
            return designerRepository.findAllBy(pageable);
        }
        else{
            return designerRepository.findAllByNameContainingOrRegionContaining(keyword, pageable);
        }
    }

    public Designer getDesignerDetail(long designerId){
        return designerRepository.findDesignerByDesignerId(designerId);
    }
    /*
    public List<Designer> getDesignerList(String keyword){
        return designerRepository.findAllByNameContaining(keyword);
    }

     */
}
