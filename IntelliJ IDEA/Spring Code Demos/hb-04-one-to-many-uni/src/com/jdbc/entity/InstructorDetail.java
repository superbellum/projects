package com.jdbc.entity;

import javax.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youTubeChannel;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Instructor instructor;

    public InstructorDetail()
    {
        //
    }

    public InstructorDetail(String youTubeChannel, String hobby)
    {
        this.youTubeChannel = youTubeChannel;
        this.hobby = hobby;
    }

    public int getId()
    {
        return id;
    }

    public String getYouTubeChannel()
    {
        return youTubeChannel;
    }

    public String getHobby()
    {
        return hobby;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setYouTubeChannel(String youTubeChannel)
    {
        this.youTubeChannel = youTubeChannel;
    }

    public void setHobby(String hobby)
    {
        this.hobby = hobby;
    }

    public Instructor getInstructor()
    {
        return instructor;
    }

    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }

    @Override
    public String toString()
    {
        return "InstructorDetail {" +
                "id=" + id +
                ", youTubeChannel='" + youTubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
