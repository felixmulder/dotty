---
title: Dotty
---

.. class:: page red exactly-one-page

    .. raw:: html

        <div id="header">
            <nav class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link" href="#why-dotty">
                        FAQ
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#getting-started">
                        Getting Started
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="https://github.com/lampepfl/dotty/wiki">
                        Wiki
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="https://github.com/lampepfl/dotty">
                        <img id="github-logo" src="/images/github-logo.svg"/>
                    </a>
                </li>
            </nav>
        </div>


    .. class:: scala-logo-container

        .. image:: /images/scala-logo.svg

        .. class:: centered-subtitle

            =====
            Dotty
            =====

            A next generation compiler for Scala


            .. raw:: html

                <a href="#why-dotty">
                    <i id="scroll-down-arrow" class="animated infinite pulse material-icons">
                        expand_more
                    </i>
                </a>

.. class:: page blue

    .. class:: centered questions

        ==========
        Why Dotty?
        ==========

        Dotty is a platform to try out new language concepts and compiler
        technologies for Scala. The focus is mainly on simplification. We
        remove extraneous syntax (e.g. no XML literals), and try to boil down
        Scala's types into a smaller set of more fundamental constructors. The
        theory behind these constructors is researched in
        `DOT<https://infoscience.epfl.ch/record/215280>`_,
        a calculus for dependent object types.

        =======================
        Is it the future Scala?
        =======================

        .. class:: centered-text

            Yes, it will be - eventually.

        =============
        Can I use it?
        =============

        .. class:: centered-text

            `You can!<#getting-started>`_ But it currently comes with no guarantees
            of stability.

        =========================
        Is there Scala 2 interop?
        =========================

        .. class:: centered-text

            Dotty currently interops with libraries compiled with Scala 2.11 - but not 2.12

        ==================================
        How do I migrate my code to Dotty?
        ==================================

        An automated rewriting tool is planned for Dotty, more on this to
        come. Scala 2 sources can be compiled to some degree by issuing:

        .. code:: bright

            ./bin/dotc -language:Scala2 file.scala

        when compiling your program.


        ====================
        Who's working on it?
        ====================

        .. class:: contributors

            .. class:: contributors-cell

                .. raw:: html

                    <div class="contributor">
                        <a href="http://github.com/odersky">
                            <img src="/images/martin.jpg">
                        </a>
                        Martin Odersky
                    </div>

                    <div class="contributor">
                        <a href="http://www.d-d.me">
                            <img src="/images/petrashko.png">
                        </a>
                        Dmitry Petrashko
                    </div>

                    <div class="contributor">
                        <a href="http://guillaume.martres.me/">
                            <img src="/images/smarter.jpg">
                        </a>
                        Guillaume Martres
                    </div>

            .. class:: contributors-cell

                .. raw:: html

                    <div class="contributor">
                        <a href="http://chaos-lab.com/">
                            <img src="/images/fengyun.png">
                        </a>
                        Liu Fengyun
                    </div>

                    <div class="contributor">
                        <a href="https://github.com/felixmulder">
                            <img src="/images/felix.jpeg">
                        </a>
                        Felix Mulder
                    </div>

        .. class:: centered-text

        And a long line of `contributors!<https://github.com/lampepfl/dotty/graphs/contributors>`_

.. class:: page teal

    .. class:: centered questions

        =============
        So, features?
        =============

        .. class:: centered-table
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Union, intersection and `literal singleton types<http://docs.scala-lang.org/sips/pending/42.type.html>`_ | Implemented         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Fast compilation (phase fusion)                                                                          | Implemented         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | `Trait parameters<http://docs.scala-lang.org/sips/pending/trait-parameters.html>`_                       | Implemented         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | `@static methods and fields<https://github.com/scala/scala.github.com/pull/491>`_                        | Implemented         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Improved REPL with colors                                                                                | Implemented         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Sbt incremental build                                                                                    | Implemented         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Non-blocking lazy vals                                                                                   | Implemented         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Option-less pattern matching (based on `name-based patmat<https://github.com/scala/scala/pull/2848>`_)   | Implemented         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Function arity adaptation                                                                                | Implemented         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Multiversal equality                                                                                     | Implemented         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        |                                                                                                          |                     |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Non-boxed arrays of value classes                                                                        | In progress         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Working contravariant implicits                                                                          | In progress         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | `Auto-Specialization<https://github.com/dotty-linker/dotty>`_                                            | In progress         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | `Whole program optimizer<https://github.com/dotty-linker/dotty>`_                                        | In progress         |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        |                                                                                                          |                     |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | HList & HMaps/Record types                                                                               | Considered          |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Implicit functions                                                                                       | Considered          |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Effects                                                                                                  | Considered          |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Auto-completion in repl                                                                                  | Considered          |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Spec Option-less pattern matching                                                                        | Considered          |
        +----------------------------------------------------------------------------------------------------------+---------------------+
        | Exhaustivity checks in pattern matching                                                                  | Considered          |
        +----------------------------------------------------------------------------------------------------------+---------------------+

        The complete list is available
        `here<https://github.com/lampepfl/dotty#what-are-the-features-that-could-make-me-consider-trying-it>`_.

        ===============
        Talks on Dotty?
        ===============

        * `Compilers are Databases<https://www.youtube.com/watch?v=WxyyJyB_Ssc>`_
          by Martin Odersky (`slides<http://www.slideshare.net/Odersky/compilers-are-databases>`_)
        * `Exploring the future of Scala<https://www.youtube.com/watch?v=aftdOFuVU1o>`_
          by Dmitry Petrashko (`slides<https://d-d.me/scalaworld2015/#/>`_)

        ======================
        I have more questions!
        ======================

        .. class:: centered-text

            That's great! We have more details on the `Github
            Wiki<https://github.com/lampepfl/dotty/wiki>`_ and please join our `Gitter channel<https://gitter.im/lampepfl/dotty>`_!


.. class:: page white

    .. class:: centered
       :style: padding-top: 32px

         ===============
         Getting Started
         ===============

         Using Dotty to compile your project is now pretty simple. Create the
         following structure:

         .. code-block:: bordered

                .
                ├── build.sbt
                ├── project
                │   ├── build.properties
                │   └── plugins.sbt
                └── src

         ---------
         build.sbt
         ---------

         .. code-block:: bordered

             name := "application"
             version := "0.1"
             enablePlugins(DottyPlugin)

         ----------------
         build.properties
         ----------------

         .. code-block:: bordered

             sbt.version=0.13.11

         -----------
         plugins.sbt
         -----------

         .. code-block:: bordered

             addSbtPlugin("com.felixmulder" % "sbt-dotty" % "0.1.3")

         This plugin is based on the
         `dotty-example-project<https://github.com/smarter/dotty-example-project>`_,
         but let's you skip building dotty from scratch.
