package com.dreamteam.SchoolSite.repositories;

import com.dreamteam.SchoolSite.models.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
}
