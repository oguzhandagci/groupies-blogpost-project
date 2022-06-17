package Blog.service;

import Blog.model.Post;
import Blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getLatestPosts(int limit) {
        List<Post> allPosts = getAllPosts();
        allPosts.sort(
                (a,b) -> -a.getCurrentDate().compareTo(b.getCurrentDate())
        );
        return allPosts.stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public void savePost(Post post) {
        this.postRepository.save(post);

    }


    @Override
    public Post getPostById(Long id) {
        Optional<Post> optional = postRepository.findById(id);
        Post post = null;
        if(optional.isPresent()){
            post = optional.get();
        }else {
            throw new RuntimeException("Post not found for id :: "+id);
        }
        return post;
    }

    @Override
    public void deletePostById(Long id) {
        this.postRepository.deleteById(id);

    }

    @Override
    public Page<Post> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
        return this.postRepository.findAll(pageable);
    }

}
