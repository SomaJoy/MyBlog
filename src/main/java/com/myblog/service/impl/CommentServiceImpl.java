package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper  modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post not found with id - "+postId)
        );
        Comment comment = mapToEntity(commentDto);
//        comment.setEmail(commentDto.getEmail());
//        comment.setText(commentDto.getText());
//        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);

        CommentDto dto = mapToDto(saveComment);
//        dto.setId(saveComment.getId());
//        dto.setEmail(saveComment.getEmail());
//        dto.setText(saveComment.getText());
        return dto;
    }

    @Override
    public void deleteComment(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Comment does not exixt with id - "+id)
        );
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post not found with id - "+id)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Comment not found with id - "+id)
        );
        //Comment updated = modelMapper.map(commentDto, Comment.class);
        //comment.setText(commentDto.getText());
        //comment.setEmail(commentDto.getEmail());
        Comment com = mapToEntity(commentDto);
        com.setId(comment.getId());
        com.setPost(post);
        Comment updatedComment = commentRepository.save(com);
        CommentDto dto = mapToDto(updatedComment);
        return dto;
    }

    public CommentDto mapToDto(Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }

    public Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

}