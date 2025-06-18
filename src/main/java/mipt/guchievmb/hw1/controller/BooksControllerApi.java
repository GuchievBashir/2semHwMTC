package mipt.guchievmb.hw1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import mipt.guchievmb.hw1.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Tag(name = "Books API", description = "Операции для работы с книгами")
public interface BooksControllerApi {

  @Operation(summary = "Получение всех книг")
  ResponseEntity<Collection<Book>> getAllBooks();

  @Operation(summary = "Получение книги по id")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Книга найдена"),
          @ApiResponse(responseCode = "404", description = "Книга не найдена")
  })
  ResponseEntity<Book> getBookById(
          @Parameter(description = "Идентификатор книги", required = true)
          @PathVariable Long id);

  @Operation(summary = "Создание книги")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = "Книга успешно создана"),
          @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
  })
  ResponseEntity<Book> createBook(
          @Parameter(description = "Объект книги для создания", required = true)
          @RequestBody @Valid Book book,
          @Parameter(description = "ID автора книги", required = true)
          @RequestParam Long authorId);

  @Operation(summary = "Удаление книги")
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = "Книга успешно удалена"),
          @ApiResponse(responseCode = "404", description = "Книга не найдена")
  })
  ResponseEntity<Void> deleteBook(
          @Parameter(description = "Идентификатор книги", required = true)
          @PathVariable Long id);
}