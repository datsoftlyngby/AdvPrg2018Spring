using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

namespace GraphAlgorithms
{
    class TestEnumerable<N> : IEnumerable<N>
    {
        public IEnumerator<N> GetEnumerator()
        {
            throw new NotImplementedException();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            throw new NotImplementedException();
        }
    }

    class TestEnumerator<N> : IEnumerator<N>
    {
        public N Current => throw new NotImplementedException();

        object IEnumerator.Current => throw new NotImplementedException();

        public void Dispose()
        {
            throw new NotImplementedException();
        }

        public bool MoveNext()
        {
            throw new NotImplementedException();
        }

        public void Reset()
        {
            throw new NotImplementedException();
        }
    }

}
