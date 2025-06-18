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

  @Operation(summary = "Получение всех книг", description = "Возвращает список всех книг")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Список успешно получен")
  })
  ResponseEntity<Collection<Book>> getAllBooks();

  @Operation(summary = "Получение книги по id", description = "Возвращает книгу по заданному идентификатору")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Книга найдена"),
          @ApiResponse(responseCode = "404", description = "Книга не найдена")
  })
  ResponseEntity<Book> getBookById(
          @Parameter(description = "Идентификатор книги", required = true)
          @PathVariable String id);

  @Operation(summary = "Создание книги", description = "Создает новую книгу")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = "Книга успешно создана"),
          @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
  })
  ResponseEntity<Book> createBook(
          @Parameter(description = "Объект книги для создания", required = true)
          @RequestBody @Valid Book book);

  @Operation(summary = "Обновление книги", description = "Полное обновление книги")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Книга успешно обновлена"),
          @ApiResponse(responseCode = "404", description = "Книга не найдена")
  })
  ResponseEntity<Book> updateBook(
          @Parameter(description = "Идентификатор книги", required = true)
          @PathVariable String id,
          @Parameter(description = "Обновленные данные книги", required = true)
          @RequestBody @Valid Book book);

  @Operation(summary = "Частичное обновление книги", description = "Обновляет только переданные поля книги")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Книга успешно обновлена"),
          @ApiResponse(responseCode = "404", description = "Книга не найдена")
  })
  ResponseEntity<Book> patchBook(
          @Parameter(description = "Идентификатор книги", required = true)
          @PathVariable String id,
          @Parameter(description = "Данные для частичного обновления книги", required = true)
          @RequestBody Book patchData);

  @Operation(summary = "Удаление книги", description = "Удаляет книгу по заданному идентификатору")
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = "Книга успешно удалена"),
          @ApiResponse(responseCode = "404", description = "Книга не найдена")
  })
  ResponseEntity<Void> deleteBook(
          @Parameter(description = "Идентификатор книги", required = true)
          @PathVariable String id);
}
