import { Comment } from "./comment.interface";

export interface PostWithComments {
  id: number;
  topic: string;
  author: string;
  title: string;
  content: string;
  comments: Comment[];
  updated_at: string;
}
