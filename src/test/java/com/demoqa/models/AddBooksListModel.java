package com.demoqa.models;


import com.demoqa.api.IsbnModel;
import lombok.Data;

import java.util.List;

@Data
public class AddBooksListModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}
