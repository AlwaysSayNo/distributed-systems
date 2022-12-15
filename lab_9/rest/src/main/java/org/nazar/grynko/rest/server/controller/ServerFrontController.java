package org.nazar.grynko.rest.server.controller;

import lombok.SneakyThrows;
import org.nazar.grynko.rest.common.model.Group;
import org.nazar.grynko.rest.server.cases.Cases;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/rest")
public class ServerFrontController {

    private final Cases cases;

    @SneakyThrows
    public ServerFrontController(Cases cases) {
        this.cases = cases;
    }

    @GetMapping("/api")
    public @ResponseBody List<Group> process(@RequestParam("op") Integer operation) {

        List<Group> result;

        switch (operation) {
            case 1: {
                result = cases.case1();
                break;
            }
            case 2: {
                result = cases.case2();
                break;
            }
            case 3: {
                result = cases.case3();
                break;
            }
            case 4: {
                result = cases.case4();
                break;
            }
            case 5: {
                result = cases.case5();
                break;
            }
            case 6: {
                result = cases.case6();
                break;
            }
            case 7: {
                result = cases.case7();
                break;
            }
            default: {
                result = null;
                break;
            }
        }

        return result;
    }

}
