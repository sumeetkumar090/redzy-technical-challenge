package com.rezdy.lunch.service;

import java.util.Comparator;

class SortByBestBefore implements Comparator<Ingredient> {
  @Override
  public int compare(Ingredient o1, Ingredient o2) {
    return o1.getBestBefore().compareTo(o2.getBestBefore());
  }
}
