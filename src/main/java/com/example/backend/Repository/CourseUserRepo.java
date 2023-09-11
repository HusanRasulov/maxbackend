package com.example.backend.Repository;

import com.example.backend.Entity.CourseUser;
import com.example.backend.Projection.CourseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CourseUserRepo extends JpaRepository<CourseUser, UUID> {
    @Query(value = """
       SELECT cu.course.id as id, cu.user.id as userId, cu.course.name as courseName, cu.course.description as courseDescription from CourseUser cu
       WHERE cu.user.id =:userId
""")
    List<CourseProjection> getCoursesAndVideosByUserId(@Param("userId") UUID userId);


    @Query(value = """
        UPDATE UserVideos SET isFinished=true WHERE user.id=:userId AND courseVideo.course.id=:youtubeId
""")
    void updateFinishedVideosByUserId(UUID userId, UUID youtubeId);
}
