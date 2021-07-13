package com.movie.reptile.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author : 765819328@qq.com
 * @version :
 * @date : Created in 2021/7/13 16:56
 * @description: 电影模型类
 * @modified By:
 */

@Data
@Builder
public class Movie {
    private String title;
    private String boxOffice;
}
