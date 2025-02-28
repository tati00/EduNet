package com.edunet.edunetback.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "content")
public class Content {
    @Id
    @Column(name = "idcontent")
    private Integer idContent;
    @Column(name = "topic1")
    private String topic1;
    @Column(name = "topic2")
    private String topic2;
    @Column(name = "topic3")
    private String topic3;
    @Column(name = "topic4")
    private String topic4;
    @Column(name = "topic5")
    private String topic5;
    @Column(name = "topic6")
    private String topic6;
    
    public Integer getIdContent() {
        return idContent;
    }
    public void setIdContent(Integer idContent) {
        this.idContent = idContent;
    }
    public String getTopic1() {
        return topic1;
    }
    public void setTopic1(String topic1) {
        this.topic1 = topic1;
    }
    public String getTopic2() {
        return topic2;
    }
    public void setTopic2(String topic2) {
        this.topic2 = topic2;
    }
    public String getTopic3() {
        return topic3;
    }
    public void setTopic3(String topic3) {
        this.topic3 = topic3;
    }
    public String getTopic4() {
        return topic4;
    }
    public void setTopic4(String topic4) {
        this.topic4 = topic4;
    }
    public String getTopic5() {
        return topic5;
    }
    public void setTopic5(String topic5) {
        this.topic5 = topic5;
    }
    public String getTopic6() {
        return topic6;
    }
    public void setTopic6(String topic6) {
        this.topic6 = topic6;
    }

    @ManyToMany(mappedBy = "content")
    private List<Course> courses;
}
