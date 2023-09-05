package br.com.tinylink.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import br.com.tinylink.api.models.Link;
import br.com.tinylink.api.models.Entry;
import br.com.tinylink.api.service.LinkService;
import br.com.tinylink.api.utils.ShortenerUtil;

@RestController
@RequestMapping("/tinylink")
public class ShortenerController {
    
    @Autowired
    LinkService linkService;

    @Autowired
    ShortenerUtil shortenerUtil;

    @GetMapping
    public List<Link> index(){
        return linkService.list();
    }
    
    @GetMapping("/{code}")
    public RedirectView redirect(@PathVariable Integer code){
        RedirectView redirectView = new RedirectView();
        Link link = linkService.findLinkByCode(code);
        redirectView.setUrl(link.getUrl());
        return redirectView;

    }
        
    @PostMapping()
    public String shortener(@RequestBody Entry entry){
        return shortenerUtil.shortener(entry.url);
    }


}
