package cn.wmyskxz.springboot.mo;

import java.util.List;
import java.util.Map;

public class SelectTermRequestMO {
    private List<Map<String, Object>> map;

    public List<Map<String, Object>> getMap() {
        return map;
    }

    public void setMap(List<Map<String, Object>> map) {
        this.map = map;
    }
}
