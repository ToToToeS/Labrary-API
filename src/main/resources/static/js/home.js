const API_BASE_URL = '/api/books';

// Helper function to display results
function displayResult(elementId, data, isError = false) {
    const element = document.getElementById(elementId);
    if (isError) {
        element.innerHTML = `<p class="error">Error: ${data}</p>`;
    } else {
        element.innerHTML = `<pre>${JSON.stringify(data, null, 2)}</pre>`;
    }
}

// Get all books
async function getAllBooks() {
    try {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
        const data = await response.json();
        displayResult('allBooksResult', data);
    } catch (error) {
        displayResult('allBooksResult', error.message, true);
    }
}

// Get book by ID
async function getBookById() {
    const bookId = document.getElementById('bookId').value;
    if (!bookId) {
        displayResult('bookResult', 'Please enter a book ID', true);
        return;
    }

    try {
        const response = await fetch(`${API_BASE_URL}/${bookId}`);
        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
        const data = await response.json();
        displayResult('bookResult', data);
    } catch (error) {
        displayResult('bookResult', error.message, true);
    }
}

// Create a new book
async function createBook() {
    const book = {
        title: document.getElementById('title').value,
        author: document.getElementById('author').value,
        year: document.getElementById('year').value ? parseInt(document.getElementById('year').value) : null,
        genre: document.getElementById('genre').value || null,
        publisher: document.getElementById('publisher').value || null,
        description: document.getElementById('description').value || null,
        isbn: document.getElementById('isbn').value || null
    };

    if (!book.title || !book.author) {
        displayResult('createBookResult', 'Title and Author are required', true);
        return;
    }

    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        });

        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
        const data = await response.json();
        displayResult('createBookResult', data);
        document.getElementById('createBookForm').reset();
    } catch (error) {
        displayResult('createBookResult', error.message, true);
    }
}

// Update a book
async function updateBook() {
    const bookId = document.getElementById('updateBookId').value;
    if (!bookId) {
        displayResult('updateBookResult', 'Please enter a book ID', true);
        return;
    }

    const book = {};
    const title = document.getElementById('updateTitle').value;
    const author = document.getElementById('updateAuthor').value;
    const year = document.getElementById('updateYear').value;
    const genre = document.getElementById('updateGenre').value;
    const publisher = document.getElementById('updatePublisher').value;
    const description = document.getElementById('updateDescription').value;
    const isbn = document.getElementById('updateIsbn').value;

    if (title) book.title = title;
    if (author) book.author = author;
    if (year) book.year = parseInt(year);
    if (genre) book.genre = genre;
    if (publisher) book.publisher = publisher;
    if (description) book.description = description;
    if (isbn) book.isbn = isbn;

    if (Object.keys(book).length === 0) {
        displayResult('updateBookResult', 'At least one field must be provided for update', true);
        return;
    }

    try {
        const response = await fetch(`${API_BASE_URL}/${bookId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        });

        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
        const data = await response.json();
        displayResult('updateBookResult', data);
        document.getElementById('updateBookForm').reset();
    } catch (error) {
        displayResult('updateBookResult', error.message, true);
    }
}

// Delete a book
async function deleteBook() {
    const bookId = document.getElementById('deleteBookId').value;
    if (!bookId) {
        displayResult('deleteBookResult', 'Please enter a book ID', true);
        return;
    }

    try {
        const response = await fetch(`/api/book/${bookId}`, {
            method: 'DELETE'
        });

        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
        displayResult('deleteBookResult', `Book with ID ${bookId} deleted successfully`);
        document.getElementById('deleteBookId').value = '';
    } catch (error) {
        displayResult('deleteBookResult', error.message, true);
    }
}