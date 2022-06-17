package Blog.controller;


import Blog.model.Post;
import Blog.service.PostService;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Entity;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("post")
public class PostController {
    @Autowired
    private PostService postService;


    @GetMapping("/showNewPostForm")
    public String showNewPostForm(Model model){
        Post post = new Post();
        model.addAttribute("post", post);
        return "new_post";
      }

      @PostMapping("/savePost")
      public String savePost(@ModelAttribute("post") Post post, Principal principal){
        String username = principal.getName();
        post.setUsername(username);
        postService.savePost(post);
        return "redirect:/";
      }

    //Emily change
    //      @Valid is added to trigger Bean Validator to check if the field conform with @NotEmpty
//    @PostMapping("/savePost")
//    public String savePost(@Valid @ModelAttribute("post") Post post,
//                           BindingResult bindingResult,
//                           Model model,
//                           RedirectAttributes attributes
////                           Principal principal
//    ){
////        If statement is included to catch error. If there is no error, the post is saved and the page will redirect.
//        if(bindingResult.hasErrors()){
//            return "new_post";
//        } else {
////            String username = principal.getName();
////            post.setUsername(username);
//            postService.savePost(post);
////              Added for success message
//            return "redirect:/showNewPostForm?success";
//        }
//    }

    @GetMapping("/showPostForUpdate/{id}")
    public String showPostForUpdate(@PathVariable(value = "id") long id, Model model){
        //get post from Blog.service
        Post post = postService.getPostById(id);
        //set post as a Blog.model attribute to pre-populate the form
        model.addAttribute("post", post);
        return "update_post";
    }
//    @PostMapping("/savePost")
//    public String createNewPost(@Valid @ModelAttribute Post post, BindingResult bindingResult, SessionStatus sessionStatus) {
//        System.err.println("POST post: " + post); // for testing debugging purposes
//        if (bindingResult.hasErrors()) {
//            System.err.println("Post did not validate");
//            return "postForm";
//        }
//        // Save post if all good
//        this.postService.savePost(post);
//        System.err.println("SAVE post: " + post); // for testing debugging purposes
//        sessionStatus.setComplete();
//        return "redirect:/post/" + post.getId();
//    }

        @GetMapping("/deletePost/{id}")
        public String deletePost(@PathVariable(value = "id") Long id, Model model){
        Post post = this.postService.getPostById(id);
        model.addAttribute("post", post);
        return "deletePost";
    }
    @GetMapping("/deletePost/{id}/confirm")
    public String deletePost(@PathVariable(value = "id") Long id){
        this.postService.deletePostById(id);
        return "redirect:/?postDeleted";
    }



//Forgot to add a proper Get Method, here is that now
    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model) {
 // get username of current logged in session user
// String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();
// find post by id
        Optional<Post> optionalPost = Optional.ofNullable(this.postService.getPostById(id));
        // if post exist put it in Blog.model
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
//            // Check if current logged in user is owner and let view template know to take according actions
//            if (authUsername.equals(post.getUser().getUsername())) {
//                Blog.model.addAttribute("isOwner", true);
//            }
            return "post";
        } else {
            return "404";
        }
    }


//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable(value = "pageNo")int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model){
//        int pageSize = 3;
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
