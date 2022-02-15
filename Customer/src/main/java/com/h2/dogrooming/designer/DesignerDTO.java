package com.h2.dogrooming.designer;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class DesignerDTO {
    private long designerId;
    private String name;
    private String profile;
    private String title;
    private String content;
    private Integer useState;

    @Builder
    public DesignerDTO(long designerId, String name, String profile, String title, String content, Integer useState){
        this.designerId = designerId;
        this.name = name;
        this.profile = profile;
        this.title = title;
        this.content = content;
        this.useState = useState;
    }

    public DesignerDTO(Designer designer){
        designerId = designer.getDesignerId();
        name = designer.getName();
        profile = designer.getProfile();
        title = designer.getTitle();
        content = designer.getContent();
        useState = designer.getUseState();
    }

    /*
    // dto to entity
    public Designer toEntity(){
        return Designer.builder()
                .designerId(designerId)
                .name(name)
                .profile(profile)
                .title(title)
                .content(content)
                .useState(useState)
                .build();
    }

    // entity to dto
    public DesignerDTO toDTO(Designer designer){
        this.designerId = designer.getDesignerId();
        this.name = designer.getName();
        this.profile = designer.getProfile();
        this.title = designer.getTitle();
        this.content = designer.getContent();
        this.useState = designer.getUseState();
    }
    */
}