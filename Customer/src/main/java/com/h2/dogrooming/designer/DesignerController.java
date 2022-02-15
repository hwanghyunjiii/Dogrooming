package com.h2.dogrooming.designer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.lang.*;


@Slf4j
@Controller
@RequestMapping("/designer")
public class DesignerController {
    private DesignerService designerService;

    public DesignerController(DesignerService designerService){
        this.designerService = designerService;
    }

    @RequestMapping(value = "/designer_search")
    public String goLogin(Authentication authentication)
    {
        return "page/designer_search";
    }

    @RequestMapping(value = "/list")
    public String getDesignerList(Model model
                                , @RequestParam(value = "keyword", required = false) String keyword
                                , @PageableDefault(page = 0, size = 6, direction = Sort.Direction.DESC) Pageable pageable) throws ParseException
    {
        Slice<Designer> designers = designerService.getDesignerList(keyword, pageable);
        model.addAttribute("designers", designers);
        model.addAttribute("keyword", keyword);

        return "page/designer";
    }

    @ResponseBody
    @RequestMapping(value = "/detail")
    public DesignerDTO getDesignerDetail(@RequestParam(value = "designerId") long designerId){
        Designer designer = designerService.getDesignerDetail(designerId);

        return new DesignerDTO(designer);
    }

    @GetMapping("/modal/detail")
    public String modalDesignerDetail() {
        return "modals/designerDetail";
    }

}
