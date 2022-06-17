package Blog.controller;

import Blog.model.Post;
import Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {

    private final PostService postService;
    @Autowired
    public HomeController(PostService postService){
        this.postService=postService;
    }
//    @GetMapping("/")
//    public String viewHomePage(Model model){
//        return findPaginated(1, "currentDate", "asc", model);
//    }

    @GetMapping("/")
    public String displayAllPosts(Model model) {
        Collection<Post> posts = this.postService.getLatestPosts(3);
        model.addAttribute("posts", posts);
        return "home";
    }



//    @GetMapping("/login")
//    public String login(Model model) {
//        return "login";
//    }

//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable(value = "pageNo")int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model){
//        int pageSize = 5;
//        Page<Post> page = postService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List<Post> listPosts = page.getContent();
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc": "asc");
//
//        model.addAttribute("listPosts", listPosts);
//        return "index";
//    }

}
