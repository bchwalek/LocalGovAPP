package pl.coderslab.gov_app.resolution;

import pl.coderslab.gov_app.sessionorder.SessionOrder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Resolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    private String resolutionName;

    private Long size;
    private Date uploadTime;

    @Lob
    private byte[] content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getResolutionName() {
        return resolutionName;
    }

    public void setResolutionName(String resolutionName) {
        this.resolutionName = resolutionName;
    }

    @ManyToOne
    public SessionOrder sessionOrder;

    public SessionOrder getSessionOrder() {
        return sessionOrder;
    }

    public void setSessionOrder(SessionOrder sessionOrder) {
        this.sessionOrder = sessionOrder;
    }
}
