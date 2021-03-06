package com.movieticketing.bo;

import com.movieticketing.model.Movie;
import com.movieticketing.model.ResultBean;

import javax.ws.rs.core.Response;

/**
 * Created by nagal_000 on 12/13/2015.
 */
public interface MovieBO {

    public ResultBean createMovie(Movie movie);

    public ResultBean getMovie(String movieId);

	public ResultBean getAllMovie();
}
