from mrjob.job import MRJob

class contarPalabras(MRJob):
    def mapper(self, _, line):
        palabras = line.split()
        for palabra in palabras:
            yield palabra, 1

    def reducer(self, palabra, counts):
        yield palabra, sum(counts)
    
if __name__ == '__main__':
    contarPalabras.run()
