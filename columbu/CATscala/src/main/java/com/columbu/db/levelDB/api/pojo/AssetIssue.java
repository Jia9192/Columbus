package com.galileo.db.levelDB.api.pojo;

import lombok.Data;

@Data(staticConstructor = "of")
public class AssetIssue {

  private String name;
  private String address;
  private long start;
  private long end;

}
