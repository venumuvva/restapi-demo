package com.orbis.api.controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// mark this class as a controller to handle /demo requests
@RestController
public class Demo {
    @RequestMapping(value = {"/VCDR/{gkfloat}/","/VCDR/"})
    public Map<String, Boolean> getDemoData(@PathVariable(required=false) String gkfloat, @RequestBody(required=false) Map<String, String> body, @RequestParam(required=false) Map<String, String> req) {
        HashMap<String, Boolean> map = new HashMap<>();
        String gkRequest=req!=null?req.get("ratio"):"";
        String gkBody= body!=null?body.get("ratio"):"";
        try {

            float gkValue = gkfloat!=null?Float.parseFloat(gkfloat):gkRequest!=null?Float.parseFloat(gkRequest):gkBody!=null?Float.parseFloat(gkBody):0;
                if(gkValue > 0 && gkValue < 1) {
                    Boolean flag = gkValue > 0.6 ? true : false;
                    map.put("glaucoma-suspect", flag);
                    return map;
                } else {
                    map.put("Invalid Request,It takes a single parameter called ratio, a float between 0 and 1\n" +
                            "– The parameter can be in the form of a URL parameter (e.g. /VCDR/0.5/), a query string\n" +
                            "(e.g. /VCDR/?ratio=0.5), or a JSON body parameter (e.g. {ratio=0.5})", false);
                    return map;
            }
        }catch(Exception e){
                map.put("Invalid Request,It takes a single parameter called ratio, a float between 0 and 1\n" +
                        "– The parameter can be in the form of a URL parameter (e.g. /VCDR/0.5/), a query string\n" +
                        "(e.g. /VCDR/?ratio=0.5), or a JSON body parameter (e.g. {ratio=0.5})", false);
                return map;
        }
    }
}