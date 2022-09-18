package com.fastcampus.pass.adapter.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
public class KakaoTalkMessageRequest {
    @JsonProperty("template_object")
    private TemplateObject templateObject;

    @JsonProperty("receiver_uuids")
    private List<String> receiverUuids;

    @Getter
    @Setter
    @ToString
    public static class TemplateObject {
        @JsonProperty("object_type")
        private String objectType;
        private String text;
        private Link link;

        @Getter
        @Setter
        @ToString
        public static class Link {
            @JsonProperty("web_url")
            private String webUrl;

        }

    }

    public KakaoTalkMessageRequest(String uuid, String text) {
        List<String> receiverUuids = Collections.singletonList(uuid);

        TemplateObject.Link link = new TemplateObject.Link();
        TemplateObject templateObject = new TemplateObject();
        templateObject.setObjectType("text");
        templateObject.setText(text);
        templateObject.setLink(link);

        this.receiverUuids = receiverUuids;
        this.templateObject = templateObject;

    }

}
